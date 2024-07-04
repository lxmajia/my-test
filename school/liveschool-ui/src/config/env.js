// 配置编译环境和线上环境之间的切换

let baseUrl = '';
let iconfontVersion = ['567566_pwc3oottzol', '1066523_v8rsbcusj5q','2078810_fz20vjqcvqg'];
let iconfontUrl = `//at.alicdn.com/t/font_$key.css`;
let codeUrl = `${baseUrl}/code`
const env = process.env
//项目路径
let studentBaseUrl = "http://localhost:8080/"
export {
    baseUrl,
    iconfontUrl,
    iconfontVersion,
    codeUrl,
    env,
    studentBaseUrl
}
