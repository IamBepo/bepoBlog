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
            <el-button @click="addVisible = true">
                <el-icon>
                    <Plus />
                </el-icon>新增
            </el-button>
        </div>
        <div style="margin-top: 10px;">
            <el-table :data="friendList" stripe style="width: 100%">
                <el-table-column prop="name" label="站名" width="180" />
                <el-table-column prop="url" label="链接" />
                <el-table-column prop="opration" label="操作">
                    <template #default="{row}">
                        <el-button type="danger" @click="handleDelete(row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>


    <el-dialog v-model="addVisible" title="新增标签" width="400px" draggable destroy-on-close>
        <el-input v-model="nameInput" placeholder="输入新增站名">
            <template #prepend>站名</template>
        </el-input>
        <el-input v-model="urlInput" placeholder="输入站点链接" style="margin-top: 10px;">
            <template #prepend>链接</template>
        </el-input>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="addVisible = false">取消</el-button>
                <el-button type="primary" @click="addFun">新增</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import { onMounted } from "@vue/runtime-core";
import { ElMessage } from 'element-plus'
import systemApi from '../../../../api/SystemApi'

    let addVisible = ref(false)
    let nameInput = ref('')
    let urlInput = ref('')
    let friendList = ref()

    function addFun(){
        let data = {
            name:nameInput.value,
            url:urlInput.value
        }
        systemApi.addFriendLink(data).then(res => {
            addVisible.value = false
            ElMessage({
                message: '新增友情链接成功.',
                type: 'success',
            })
        })
    }

    function listFriend(){
        systemApi.getFriendKLinkList().then(res => {
            friendList.value = res.data.data
        })
    }

    function handleDelete(row){
        systemApi.removeFriendLink(row.id).then(res => {
            ElMessage({
                message: '删除友情链接成功.',
                type: 'success',
            })
        })
    }

    onMounted:{
        listFriend()
    }
</script>
