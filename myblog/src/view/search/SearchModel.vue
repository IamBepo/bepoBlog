<template>
    <view style="margin: 20px 0;display: flex;justify-content: center;">
        <view>
            <div
                style="height: 35px;box-shadow: 0 4px 8px 6px rgb(113 118 122 / 6%);margin-bottom: 12px;border-radius: 13px;background-color: white;">
                <div style="display: flex;align-items: center;height: 100%;padding-left: 10px;color: #918282;font-size: 17px;">
                    <div style="padding-left: 8px;">
                        为您找到 <label style="color: #ff0a0a;">{{searchContent}}</label> 相关文章约{{listSize}}篇
                    </div>
                </div>
            </div>

            <PostBox v-for="item in list" :key="item.id" :list="item"></PostBox>

            <div style="display: flex;justify-content: center;">
                <div>
                    <el-pagination background layout="prev, pager, next" :total="1000">
                    </el-pagination>
                </div>
            </div>
        </view>
    </view>
</template>

<script setup>
import PostBox from '@/components/PostBox.vue'
import { ref } from '@vue/reactivity'
import articleApi from '@/api/ArticleApi'
import { onActivated, onMounted } from '@vue/runtime-core'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { useRoute } from 'vue-router'

    let list = ref()
    const route = useRoute()
    let searchContent = ref()
    let listSize = ref()

    function listArticle(){ 
        let data = {
            key:searchContent.value
        }
        articleApi.getSearchArticle(data).then(res => {
            list.value = res.data.data
            listSize.value = list.value.length
            NProgress.done()
        })
    }

    onActivated:{
        NProgress.start()
        searchContent.value = route.query.keyWord
        listArticle()
    }
</script>