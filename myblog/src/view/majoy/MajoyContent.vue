<template>
    <div class="Container">
        <div class="LContainer">
            <div style="margin:0 0 10px 0;">
                <TopSwiper></TopSwiper>
            </div>
            <div>
                <Propagate></Propagate>
            </div>
            <div>
                <PostBox v-for="item in articleList" :key="item.id" :list="item"></PostBox>
            </div>
            <div>
                <div class="pagination">
                    <div>
                        <el-pagination background layout="prev, pager, next" :total="1000">
                        </el-pagination>
                    </div>
                </div>
            </div>
        </div>
        <div class="RContainer">
            <div>
                <RightBloggerInfo></RightBloggerInfo>
            </div>
            <div style="padding-top: 10px;">
                <RightOtherInfo :list="tabList"></RightOtherInfo>
                <RightHeatInfo></RightHeatInfo>
                <RightNewComment :list="newCommentList"></RightNewComment>
            </div>
        </div>
    </div>
</template>

<script setup>
import PostBox from '../../components/PostBox.vue';
import RightBloggerInfo from '../../components/RightBloggerInfo.vue';
import RightOtherInfo from '../../components/RightOtherInfo.vue';
import RightHeatInfo from '../../components/RightHeatInfo.vue';
import PostBox_Right from '../../components/PostBox_Right.vue';
import Propagate from '../../components/Propagate.vue';
import TopSwiper from '../../components/TopSwiper.vue';
import RightNewComment from '../../components/RightNewComment.vue';
import { inject, onMounted, ref } from '@vue/runtime-core';
import { ElMessage } from 'element-plus'

    const axios = inject('$axios')
    let articleList = ref()
    let tabList = ref()
    let newCommentList = ref()

    function listArticle(){
        axios({
            url:'/blog/list/home',
            method:'GET'
        }).then(res => {
            if(res.data.code === '200'){
                articleList.value = res.data.data
            }else{
                ElMessage({
                    message: '网络似乎出现了问题.',
                    type: 'error',
                })
            }
        }).catch(error =>{
            ElMessage({
                message: '网络似乎出现了问题.',
                type: 'error',
            })
        })
    }

    function listTab(){
        axios({
            url:'/blog/tab/list',
            method:'GET'
        }).then(res => {
            if(res.data.code === '200'){
                tabList.value = res.data.data
            }else{
                ElMessage({
                    message: '网络似乎出现了问题.',
                    type: 'error',
                })
            }
        }).catch(error =>{
            ElMessage({
                message: '网络似乎出现了问题.',
                type: 'error',
            })
        })
    }

    function listNewComment(){
        axios({
            url:'/comment/list/new',
            method:'GET'
        }).then(res => {
            if(res.data.code === '200'){
                newCommentList.value = res.data.data
            }else{
                ElMessage({
                    message: '网络似乎出现了问题.',
                    type: 'error',
                })
            }
        }).catch(error =>{
            ElMessage({
                message: '网络似乎出现了问题.',
                type: 'error',
            })
        })
    }
    onMounted:{
        listArticle()
        listTab()
        listNewComment()
    }

</script>

<style scoped>
    .Container {
        display: flex;
        justify-content: center;
        margin: 30px 50px 10px 50px;
    }
    .LContainer {
        width: 890px;
        padding: 0 20px;
        
    }
    .RContainer {
        width: 16%;
    }
    ::v-deep .el-pagination.is-background .el-pager li{
        background-color: #ededed;
    }
    ::v-deep .el-pagination.is-background .btn-prev, ::v-deep .el-pagination.is-background .btn-next{
        background-color: #ededed;
    }
    .pagination {
        display: flex;
        justify-content: center;
    }
</style>