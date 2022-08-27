<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">访问记录</a></el-breadcrumb-item>
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
                <el-icon>
                    <CloseBold />
                </el-icon>批量删除
            </el-button>
        </div>
        <div style="margin-top: 10px;">
            <el-table :data="tableData" stripe style="width: 100%" @selection-change="handleSelectionChange" border>
                <el-table-column type="selection" width="55" />
                <el-table-column prop="updateTime" label="访问描述" width="170" />
                <el-table-column prop="title" label="请求接口" width="350" show-overflow-tooltip resizable />
                <el-table-column prop="title" label="请求参数" width="350" show-overflow-tooltip resizable />
                <el-table-column prop="title" label="访问Ip" width="350" show-overflow-tooltip resizable />
                <el-table-column prop="title" label="访问时间" show-overflow-tooltip resizable />
            </el-table>
        </div>
        <div style="margin-top: 20px;">
            <el-pagination background layout="prev, pager, next" :total="50" class="mt-4" />
        </div>
    </div>
</template>