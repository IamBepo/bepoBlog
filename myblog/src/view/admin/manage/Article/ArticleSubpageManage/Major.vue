<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">文章管理</a></el-breadcrumb-item>
        </el-breadcrumb>
    </div>
    <div style="background-color: white;margin: 20px 0;padding: 20px;">
        <div style="display: flex;">
            <el-button type="primary">
                <el-icon>
                    <Refresh />
                </el-icon>
            </el-button>
            <el-button type="info" @click="$router.push({name:'articleAdd'})">
                <el-icon>
                    <EditPen />
                </el-icon>发表文章
            </el-button>
            <el-button type="danger" @click="mulDelete">
                <el-icon>
                    <CloseBold />
                </el-icon>批量删除
            </el-button>
            <div style="padding-left: 20px;">
                <el-input placeholder="Search.." v-model="searchValue" clearable prefix-icon="Search"
                    @keydown.enter.native="searchFun()">
                </el-input>
            </div>
        </div>
        <div style="margin-top: 10px;">
            <el-table :data="tableData" stripe style="width: 100%" @selection-change="handleSelectionChange" border>
                <el-table-column type="selection" width="55" />
                <el-table-column prop="updateTime" label="更新时间" width="170" />
                <el-table-column prop="title" label="标题" width="350" show-overflow-tooltip resizable />
                <el-table-column prop="sortName" label="分类" width="130">
                    <template #default="{row}">
                        <el-tag class="mx-1" size="large">{{row.sortName}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="viewCount" label="浏览" width="70" />
                <el-table-column prop="commentCount" label="评论" width="70" />
                <el-table-column prop="status" label="启动" width="70">
                    <template #default="{row}">
                        <el-switch v-model="row.status" class="mt-2" inline-prompt @change="statusChange(row)"
                            :active-value=1 :inactive-value=0 />
                    </template>
                </el-table-column>
                <el-table-column prop="topStatus" label="置顶" width="70">
                    <template #default="{row}">
                        <el-switch v-model="row.topStatus" class="mt-2" inline-prompt @change="topStatusChange(row)"
                            :active-value=1 :inactive-value=0 />
                    </template>
                </el-table-column>
                <el-table-column prop="recommendStatus" label="推荐" width="70">
                    <template #default="{row}">
                        <el-switch v-model="row.recommendStatus" class="mt-2" inline-prompt @change="recommendStatusChange(row)"
                            :active-value=1 :inactive-value=0 />
                    </template>
                </el-table-column>
                <el-table-column prop="commentStatus" label="评论" width="70">
                    <template #default="{row}">
                        <el-switch v-model="row.commentStatus" class="mt-2" inline-prompt @change="commentStatusChange(row)"
                            :active-value=1 :inactive-value=0 />
                    </template>
                </el-table-column>
                <el-table-column prop="id" label="操作">
                    <template #default="{row}">
                        <el-button @click="handleEdit(row.id)">编辑</el-button>
                        <el-button type="danger" @click="handleDelete(row.id)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div style="margin-top: 20px;">
            <el-pagination background layout="prev, pager, next" :total="50" class="mt-4" />
        </div>
    </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import { onMounted } from "@vue/runtime-core";
import { ElMessage } from 'element-plus'
import articleApi from '../../../../../api/ArticleApi'
import recommendApi from '../../../../../api/RecommendApi'

    let searchValue = ref('')

    let checkedData = ref([''])
    const tableData = ref([])

    function searchFun(){
        let data = {
            content:searchValue.value
        }
        articleApi.getSearchArticleList(data).then(res => {
            tableData.value = res.data.data
        })
    }

    function statusChange(row){
        let data = {
            id:row.id,
            status:row.status
        }
        articleApi.updateArticleStatus(data).then(res => {
        })
    }

    function recommendStatusChange(row){
        let data = {
            id:row.id,
            status:row.recommendStatus
        }
        recommendApi.updateArticleRecommendStatus(data).then(res => {
        })
    }

    function commentStatusChange(row){
        let data = {
            id:row.id,
            status:row.commentStatus
        }
        articleApi.updateArticleCommentStatus(data).then(res => {})
    }

    function topStatusChange(row){
        let data = {
            id:row.id,
            status:row.topStatus
        }
        recommendApi.updateArticleTopStatus(data).then(res => {})
    }

    function handleSelectionChange(value){
        checkedData.value = value
    }

    function mulDelete(){
        let arrId = []
        for(let i = 0;i<checkedData.value.length;i++){
            arrId.push(checkedData.value[i].id)
        }
        articleApi.removeMulArticle(arrId).then(res => {
            listArticle()
            ElMessage({
                message: '删除成功.',
                type: 'success',
            })
        })
    }

    function listArticle(){
        articleApi.getAdminArticleList().then(res => {
            tableData.value = res.data.data
        })
    }

    function handleDelete(id){
        articleApi.removeOnceArticle().then(res => {
            listArticle()
            ElMessage({
                message: '删除成功.',
                type: 'success',
            })
        })
    }

    onMounted:{
        listArticle()
    }

</script>