<template>
    <div class="Container">
        <div class="LContainer">
            <div style="margin:0 0 10px 0;">
                <TopSwiper :list="walkRecommendList"></TopSwiper>
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
                <RightBloggerInfo :list="shieldsList"></RightBloggerInfo>
            </div>
            <div style="padding-top: 10px;">
                <RightOtherInfo :list="tabList"></RightOtherInfo>
                <RightHeatInfo :list="listRecommendList"></RightHeatInfo>
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
import articleApi from '../../api/ArticleApi'
import aggregationApi from '@/api/AggregationApi';
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

    let articleList = ref()
    let tabList = ref()
    let newCommentList = ref()
    let listRecommendList = ref()
    let walkRecommendList = ref()
    let shieldsList = ref()

    // function listArticle(){ 
    //     articleApi.getArticleList().then(res => {
    //         articleList.value = res.data.data
    //     })
    // }

    // function listTab(){
    //     articleApi.getTabList().then(res => {
    //         tabList.value = res.data.data
    //     })
    // }

    // function listNewComment(){
    //     articleApi.getNewCommentList().then(res => {
    //         newCommentList.value = res.data.data
    //     })
    // }

    // function listListRecommend(){
    //     articleApi.getRecommendList().then(res => {
    //         listRecommendList.value = res.data.data
    //     })
    // }

    // function listWalkRecommend(){
    //     articleApi.getWalkRecommendList().then(res => {
    //         walkRecommendList.value = res.data.data
    //     })
    // }

    // function listShields(){
    //     articleApi.getShieldsList().then(res => {
    //         shieldsList.value = res.data.data
    //     })
    // }

    function home(){
        aggregationApi.getHomeShow().then(res => {
            let home = res.data.data
            articleList.value = home.article
            tabList.value = home.tab
            newCommentList.value = home.newComment
            listRecommendList.value = home.listRecommend
            walkRecommendList.value = home.walkRecommend
            shieldsList.value = home.shields
            NProgress.done()
        })

    }


    onMounted:{
        NProgress.start()
        // listArticle()
        // listTab()
        // listNewComment()
        // listListRecommend()
        // listWalkRecommend()
        // listShields()
        home()
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
        min-width: 890px;
    }
    .RContainer {
        width: 290px;
        min-width: 290px;
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