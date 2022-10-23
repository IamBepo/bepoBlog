<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">用户列表</a></el-breadcrumb-item>
        </el-breadcrumb>
    </div>

    <div style="background-color: white;margin: 20px 0;padding: 20px;">
        <div style="display: flex;">
            <el-button type="primary">
                <el-icon>
                    <Refresh />
                </el-icon>
            </el-button>
            <el-button type="danger" @click="mulLock">
                <el-icon><Lock /></el-icon>
                批量锁定
            </el-button>
            <div style="padding-left: 20px;">
                <el-input placeholder="Search.." v-model="searchValue" clearable prefix-icon="Search"
                    @keydown.enter.native="searchFun()">
                </el-input>
            </div>
        </div>
        <div style="margin-top: 10px;">
            <el-table :data="dataList" stripe style="width: 100%" @selection-change="handleSelectionChange" border>
                <el-table-column type="selection" width="55" />
                <el-table-column prop="userInfo.userid" label="用户名" width="120" />
                <el-table-column prop="userInfo.name" label="昵称" width="150" show-overflow-tooltip resizable />
                <el-table-column prop="userInfo.email" label="邮箱" width="200" show-overflow-tooltip resizable />
                <el-table-column prop="powerRole.name" label="权限" width="120" show-overflow-tooltip resizable />
                <el-table-column prop="powerRole.name" label="最近访问ip" width="100" show-overflow-tooltip resizable />
                <el-table-column prop="powerRole.name" label="锁定" width="70">
                    <!-- <template #default="{row}">
                        <el-switch v-model="row.name" class="mt-2" inline-prompt @change="statusChange(row)"
                            :active-value=1 :inactive-value=0 />
                    </template> -->
                </el-table-column>
                <el-table-column prop="id" label="操作">
                    <template #default="{row}">
                        <el-button @click="handleEdit(row.id)">编辑信息</el-button>
                        <el-button @click="handleEdit(row.id)">更改权限</el-button>
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
import userApi from '../../../../api/UserApi'

    let searchValue = ref('')
    let dataList = ref([])
    let checkedData = ref()

    function handleSelectionChange(value){
        checkedData.value = value
    }

    function searchFun(){

    }

    function listUserInfo(){
        userApi.getUserInfoList().then(res => {
            dataList.value = res.data.data
            console.log(res.data.data)
        })
    }

    function mulLock(){
        let arrId = []
        for(let i = 0;i<checkedData.value.length;i++){
            arrId.push(checkedData.value[i].id)
        }
    }

    mounted:{
        listUserInfo()
    }
</script>
