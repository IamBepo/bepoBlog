<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">友情链接</a></el-breadcrumb-item>
        </el-breadcrumb>
    </div>
    <div style="background-color: white;margin: 20px 0;padding: 20px;">
        <div style="display: flex;">
            <el-button type="primary">
                <el-icon>
                    <Refresh />
                </el-icon>
            </el-button>
            <el-button @click="LabelVisible = true">
                <el-icon>
                    <Plus />
                </el-icon>新增
            </el-button>
        </div>
        <div style="margin-top: 10px;">
            <el-table :data="tableData" stripe style="width: 100%">
                <el-table-column prop="name" label="站名" width="180" />
                <el-table-column prop="name" label="链接" />
            </el-table>
        </div>
    </div>
</template>