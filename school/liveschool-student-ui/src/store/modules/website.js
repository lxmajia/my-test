/**
 * 站点缓存
 */
import {websiteProfile} from "@/api";
import { setStore, getStore } from '@/util/store'
import { validatenull } from '@/util/validate';
import {websiteNavigate} from "@/api";

const website = {
  state: {
    websiteConfig: getStore({ name: 'websiteConfig' }) || {},//站点设置
    websiteNavigate: getStore({ name: 'websiteNavigate' }) || [],//站点导航
    bottomLink: getStore({ name: 'bottomLink' }) || [],//底部标签
    friendLink: getStore({ name: 'friendLink' }) || [],//友情链接
  },
  mutations:{
    SET_WESITECONFIG: (state,websiteConfig) =>{
        state.websiteConfig = websiteConfig
    },
    SET_WEBSITENAVIGATE: (state,websiteNavigate) =>{
      state.websiteNavigate = websiteNavigate
    },
    SET_BOTTOMLINK: (state,bottomLink) =>{
      state.bottomLink = bottomLink
    },
    SET_FRIENDLINK: (state,friendLink) =>{
      state.friendLink = friendLink
    },
  },
  actions: {
    getWebsiteConfig({state,commit}){
      return new Promise((resolve, reject) => {
        //判断如果站点配置有值则不更新数据
        if(validatenull(state.websiteConfig)){
          const webSiteInfo = {
            "copyright": "Copyright ©2023 版权所有   <a href=\"#void(0)\">津ICP备19004321号-2</a>",
            "keywords": "在线网校,网校系统,在线教育,网校平台,智慧教育",
            "qqOpenFlag": "1",
            "description": "在线教育平台搭建商",
            "weiboOpenFlag": "2",
            "publicKey": "xxxxxx",
            "title": "网校系统",
            "qqClientSecret": "xxxxxx",
            "smsloginFlag": "1",
            "appId": "xxxxxx",
            "qqClientId": "xxxxxx",
            "logo": "http://wxdemo.liangzhikeji.cn/upload/image/20230306/1263189383568711.png",
            "aliFlag": "1",
            "weiboClientId": "xxxxxx",
            "company": "网校系统",
            "wxMchId": "xxxxxx",
            "email": "service@126.com     投诉电话：400-***-****",
            "weiboClientSecret": "xxxxxx",
            "author": "网校系统",
            "wechatOpenFlag": "1",
            "AppSecret": "xxxxxx",
            "smsOpenFlag": "2",
            "privateKey": "xxxxxx",
            "phone": "400-***-****",
            "loginLogo": "http://wxdemo.liangzhikeji.cn/upload/image/20230306/1263189383568711.png",
            "wechatClientId": "xxxxxx",
            "wechatClientSecret": "xxxxxx",
            "bottonLogo": "http://wxdemo.liangzhikeji.cn/upload/image/20221117/77082907318184326.jpg",
            "information": "",
            "censusCodeString": "",
            "wechatFlag": "1"
          };

            commit('SET_WESITECONFIG',webSiteInfo);
            //将数据存入本地浏览器seession
            setStore({ name: 'websiteConfig', content: webSiteInfo ,type: 'session'})
            resolve(webSiteInfo);
        }else{
          resolve(state.websiteConfig);
        }
      })

    },
    getWebsiteNavigate({state,commit}){
      //判断如果站点配置有值则不更新数据
      if(validatenull(state.websiteNavigate)){
        websiteNavigate("1").then(res =>{
          commit('SET_WEBSITENAVIGATE',res.data.data);
          //将数据存入本地浏览器seession
          setStore({ name: 'websiteNavigate', content: res.data.data,type: 'session' })
        })
      }
    },
    getBottomLink({state,commit}){
      //判断如果站点配置有值则不更新数据
      if(validatenull(state.bottomLink)){
        websiteNavigate("4").then(res =>{
          commit('SET_BOTTOMLINK',res.data.data);
          //将数据存入本地浏览器seession
          setStore({ name: 'bottomLink', content: res.data.data,type: 'session' })
        })
      }
    },
    getFriendLink({state,commit}){
      //判断如果站点配置有值则不更新数据
      if(validatenull(state.bottomLink)){
        websiteNavigate("3").then(res =>{
          commit('SET_FRIENDLINK',res.data.data);
          //将数据存入本地浏览器seession
          setStore({ name: 'friendLink', content: res.data.data,type: 'session' })
        })
      }
    }
  },
  getters: {
    website: state => {
      return state.websiteConfig
    },
    navigation: state => {
      return state.websiteNavigate
    },
    bottomLink: state => {
      return state.bottomLink
    },
    friendLink: state => {
      return state.friendLink
    }
  }

}
export default website
