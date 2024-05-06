package cn.xwlin.agent;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class DemoAgent {

    private static final String AGENT_METHOD_SUFFIX = "$lxAgentMethod";
    private static final String returnSource = "{" + "long s = System.currentTimeMillis();" + "Object result;" + "try{" + "result = ($w)%s" + AGENT_METHOD_SUFFIX + "($$);" + "} finally {" + "long e = System.currentTimeMillis();" + "System.out.println((e-s) + \"ms\");" + "}" + "return ($r) result;" + "}";
    private static final String voidSource = "{" + "long s = System.currentTimeMillis();" + "try{" + "%s" + AGENT_METHOD_SUFFIX + "($$);" + "} finally {" + "long e = System.currentTimeMillis();" + "System.out.println((e-s) + \"ms\");" + "}" + "}";

    /**
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中
     */
    public static void premain(String arg, Instrumentation instrumentation) {
        System.out.println("agent的premain(String arg, Instrumentation instrumentation)方法");

        String businessAgentPackage = "cn.xwlin";


        final ClassPool classPool = new ClassPool();
        classPool.appendSystemPath();
        instrumentation.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if (className != null && className.replace("/", ".").startsWith(businessAgentPackage)) {
                    try {
                        CtClass ctClass = classPool.get(className.replace("/", "."));
                        CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
                        for (CtMethod declaredMethod : declaredMethods) {
                            if ("main".equals(declaredMethod.getName())) {
                                continue;
                            }
                            // 复制一个新方法加到类中，方法名：[oldMethodName]$lxAgentMethod
                            CtMethod copyMethod = CtNewMethod.copy(declaredMethod, ctClass, null);
                            copyMethod.setName(declaredMethod.getName() + AGENT_METHOD_SUFFIX);
                            declaredMethod.getDeclaringClass().addMethod(copyMethod);
                            // 修改老方法内容
                            CtClass returnType = declaredMethod.getReturnType();
                            if (returnType.equals(CtClass.voidType)) {
                                declaredMethod.setBody(String.format(voidSource, declaredMethod.getName()));
                            } else {
                                declaredMethod.setBody(String.format(returnSource, declaredMethod.getName()));
                            }
                        }
                        return ctClass.toBytecode();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                return null;
            }
        });
    }

}