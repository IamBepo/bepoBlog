<template>
    <div style="display: flex;justify-content: center;margin: 10px 0;">
        <div style="background-color: white;min-height: 100px;width: 1000px;border-radius: 10px;padding: 20px;">
            <div @click="test" style="text-align: center;padding: 0px 32px;">
                <h1
                    style="font-family: -apple-system,BlinkMacSystemFont,Segoe UI,Helvetica,Arial,sans-serif,Apple Color Emoji,Segoe UI Emoji;">
                    {{articleList.title}}</h1>
            </div>
            <div style="color: #999999;font-size: 14px;padding-top: 5px;text-align: center;">
                <label>{{articleList.sortName}}</label>
                <label style="padding-left: 10px;">{{articleList.updateTime}}</label>
                <label style="padding-left: 15px;">{{articleList.viewCount}}阅读</label>
                <label style="padding-left: 10px;">·</label>
                <label style="padding-left: 10px;">{{articleList.commentCount}}评论</label>
            </div>
            <!-- <el-divider /> -->
            <v-md-preview :text="articleList.article.content" ref="preview"></v-md-preview>
            <div style="padding: 0px 32px;display: flex;align-items: center;">
                <el-icon>
                    <PriceTag />
                </el-icon>
                <el-link style="margin-left: 10px;" type="success" v-for="item in articleList.blogTab">{{item.name}}</el-link>
            </div>
        </div>
        <!-- <div style="position: absolute;right: 50px;">
            <div style="height: 500px;width: 200px">
                <div>
                    哈哈哈哈
                </div>
                <div v-for="anchor in titles" :style="{ padding: `10px 0 10px ${anchor.indent * 20}px` }"
                    @click="handleAnchorClick(anchor)">
                    <a style="cursor: pointer">{{ anchor.title }}</a>
                </div>
            </div>
        </div> -->
    </div>
    <PostComment :articleId="articleList.id" :list="commentList"></PostComment>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import {useRoute} from 'vue-router'
import { getCurrentInstance, inject, onMounted } from "@vue/runtime-core";
import { ElMessage } from 'element-plus'
import PostComment from '../../components/PostComment.vue'

    const { proxy } = getCurrentInstance()
    const axios = inject('$axios')
    const preview = ref()
    const route = useRoute()
    let articleList = ref({
        title:'标题模板',
        updateTime:'1970-01-01',
        viewCount:999,
        commentCount:999,
        article:{
            content:'内容模板'
        }
    })
    let commentList = ref()

    // let titles = ref([
    //     {
    //         indent: 0,
    //         lineIndex: null,
    //         title: "Markdown Editor built on Vue"
    //     }
    // ])

    function listArticle(id){
        axios({
            url: '/blog/read/' + id,
            method: 'GET'
        }).then(res => {
            if(res.data.code === '200'){
                articleList.value = res.data.data
                console.log(articleList.value)
            }else{
                ElMessage({
                    message: '网络似乎出现了问题.',
                    type: 'error',
                })
            }
        }).catch(error => {
            ElMessage({
                message: '网络似乎出现了问题.',
                type: 'error',
            })
        })
    }

    // function test(){
    //     const anchors = proxy.$refs.preview.$el.querySelectorAll('h1,h2,h3,h4,h5,h6')
    //     const titles = Array.from(anchors).filter((title) => !!title.innerText.trim());

    //     if (!titles.length) {
    //         titles.value = [];
    //         return;
    //     }

    //     const hTags = Array.from(new Set(titles.map((title) => title.tagName))).sort();

    //     titles.value = titles.map((el) => ({
    //         title: el.innerText,
    //         lineIndex: el.getAttribute('data-v-md-line'),
    //         indent: hTags.indexOf(el.tagName),
    //     }));
    //     console.log(titles.value)
    // }

    // function handleAnchorClick(){
    //     console.log(titles.value)
    // }

    function listComment(id){
        axios({
            url: '/comment/list/' + id,
            method: 'GET',
        }).then(res => {
            if(res.data.code === '200'){
                commentList.value = res.data.data
                for(let i = 0;i<commentList.value.length;i++){
                    commentList.value[i].replyInputVisble = false
                }
                console.log(commentList.value)
            }else{
                ElMessage({
                    message: '网络似乎出现了问题.',
                    type: 'error',
                })
            }
        }).catch(error => {
            ElMessage({
                message: '网络似乎出现了问题.',
                type: 'error',
            })
        })
    }

    onMounted:{
        listArticle(route.params.id)
        listComment(route.params.id)
    }
</script>

<style scoped>
    h1{
        margin: 5px 0;
    }
    .el-divider--horizontal {
        margin: 10px 0;
    }
</style>