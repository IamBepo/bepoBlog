<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">评论管理</a></el-breadcrumb-item>
        </el-breadcrumb>
    </div>

    <div style="background-color: white;margin: 20px 0;padding: 20px;">
        <div style="display: flex;">
            <el-button type="primary">
                <el-icon>
                    <Refresh />
                </el-icon>
            </el-button>
            <el-button type="danger" @click="mulDelete">
                <el-icon><CloseBold /></el-icon>
                批量删除
            </el-button>
            <div style="padding-left: 20px;">
                <el-input placeholder="Search.." v-model="searchValue" clearable prefix-icon="Search"
                    @keydown.enter.native="searchFun()">
                </el-input>
            </div>
        </div>
        <div style="margin-top: 10px;">
            <el-table :data="commentList" stripe style="width: 100%" @selection-change="handleSelectionChange" border>
                <el-table-column type="selection" width="55" />
                <el-table-column prop="name" label="用户名" width="170" />
                <el-table-column prop="title" label="文章标题" width="100">
                </el-table-column>
                <el-table-column prop="content" label="评论" width="800">
                </el-table-column>
                <el-table-column prop="id" label="操作">
                    <template #default="{row}">
                        <el-button type="danger" @click="handleDelete(row)">删除
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
import { onMounted, ref } from "@vue/runtime-core";
import { ElMessage } from 'element-plus'
import articleApi from '../../../../api/ArticleApi'

    let commentList = ref()
    let searchValue =ref('')
    let selectedData = ref('')

    function listComment(){
        articleApi.getAllArticleCommentList().then(res => {
            commentList.value = res.data.data
        })
    }

    function searchFun(){

    }

    function handleSelectionChange(value){
        selectedData.value = value
    }

    function mulDelete(){
        articleApi.removeMulComment(selectedData.value).then(res => {
            ElMessage({
                message: '删除评论成功.',
                type: 'success',
            })
        })
    }

    function handleDelete(row){
        let data = {
            id:row.id,
            type:row.type
        }
        articleApi.removeOnceComment(data).then(res => {
            ElMessage({
                message: '删除评论成功.',
                type: 'success',
            })
        })
    }

    onMounted:{
        listComment()
    }
</script>