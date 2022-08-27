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
            <el-table :data="tableData" stripe style="width: 100%" @selection-change="handleSelectionChange" border>
                <el-table-column type="selection" width="55" />
                <el-table-column prop="updateTime" label="用户名" width="170" />
                <el-table-column prop="status" label="文章标题" width="100">
                    <template #default="{row}">
                        <el-switch v-model="row.status" class="mt-2" inline-prompt @change="statusChange(row)"
                            :active-value=1 :inactive-value=0 />
                    </template>
                </el-table-column>
                <el-table-column prop="commentStatus" label="评论" width="700">
                    <template #default="{row}">
                        <el-switch v-model="row.commentStatus" class="mt-2" inline-prompt @change="commentStatusChange(row)"
                            :active-value=1 :inactive-value=0 />
                    </template>
                </el-table-column>
                <el-table-column prop="id" label="操作">
                    <template #default="{row}">
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
</script>