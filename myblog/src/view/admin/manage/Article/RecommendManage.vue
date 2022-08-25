<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">推荐管理</a></el-breadcrumb-item>
        </el-breadcrumb>
    </div>
    <div style="background-color: white;margin: 20px 0;padding: 20px;">
        <div style="display: flex;">
            <el-button type="primary">
                <el-icon>
                    <Refresh />
                </el-icon>
            </el-button>
            <el-button>
                <el-icon>
                    <Plus />
                </el-icon>新增
            </el-button>
        </div>
        <div style="display: flex;width: 100%;padding-top: 20px;">
            <img style="width: 200px;height: 150px;" src="http://up.llxuo.top/autospoofingAPP/05e48ca0-85c5-43ab-808a-cb73487f868f.jpg" />
        </div>
        <div style="margin-top: 20px;">
            <el-pagination background layout="prev, pager, next" :total="50" class="mt-4" />
        </div>
    </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";


    const tableData = [
        {
            name: 'Springboot',
            enable:true
        },
        {
            name: 'Springboot'
        },
        {
            name: 'Springboot'
        },
        {
            name: 'Springboot'
        },
    ]

</script>

<style scoped>

</style>