<template>

</template>

<script>
  import {articleList} from "@/api";
  import { setStore, getStore } from '@/util/store'
  import {getTeacherList} from "@/api/teacher";
  export default {
    name: 'hotArticle',
    data() {
      return {
        //教师列表数据
        teacherList:[],
        current:1,
        size:10,
      }
    },
    components: {},
    methods: {
      init(){
        //this.getTeacherList();
      },
      getTeacherList(){//查询教师列表
        // let params = {
        //   "current": this.current,
        //   "size": this.size,
        // }
        // getTeacherList(params).then(res=>{
        //   this.teacherList=res.data.data.records;
        //   this.page = res.data.data
        // })
      }
    },
    mounted:function() {
      this.init();
    }
  }
</script>

<style scoped>
  .container-types {
    border-radius: 8px;
  }
  .title-top-box{
    height: 68px;
    margin-bottom: 30px;
  }
  .title-top-box h3{
    font-size: 36px!important;
    font-weight: 700!important;
    color: #262c3a!important;
    letter-spacing: 0!important;
    line-height: 36px;
    position: relative;
    text-align: center;
  }
  .teacher-info-li{
    float: left!important;
    width: 18%!important;
    margin: 20px 28px 0px 0px;
    border-radius: 8px;
    position: relative;
  }
  .teacher-info-li:nth-child(5n){

    margin-right: 0px;
  }
  .teacher-info-li .teacher-photo{
    width: 100%;
    border-radius: 8px;
  }
  .teacher-connect-bottom{
    width: 100%;
    border-radius: 0 0 8px 8px;
    font-size: 18px;
    display: block;
    position: absolute;
    bottom: 5px;
    left: 0;
    background: #000;
    opacity: .6;
    color: #fff;
    text-align: center;
  }
  .teacher-connect{
    width: 100%;
    height: 98%;
    border-radius: 8px;
    font-size: 20px;
    position: absolute;
    bottom: 5px;
    left: 0;
    background: #000;
    opacity: .6;
    color: #fff;
    text-align: center;
  }
  .teacher-connect-top{
    width: 100%;
    border-radius: 8px;
    padding-top: 20px;
  }
  .teacher-connect-down{
    width: 100%;
    border-radius: 8px;
    padding-top: 20px;
    font-size: 16px;
  }
  .teacher-connect-img img{
    height: 10px;
    width: 12px;
  }
  .teacher-info-li:hover .teacher-connect{
    display: block!important;
  }
  .teacher-info-li:hover .teacher-connect-bottom{
    display: none!important;
  }
</style>
