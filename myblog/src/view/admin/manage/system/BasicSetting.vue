<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">基本设置</a></el-breadcrumb-item>
        </el-breadcrumb>
    </div>

    <div style="margin-top: 20px;">
        <el-tabs type="border-card" class="demo-tabs">
            <el-tab-pane label="基本信息">
                <el-form label-width="120px">
                    <el-form-item label="博客标题">
                        <el-input style="width: 500px;" v-model="$store.state.systemInfo.webTitle"
                            placeholder="博客页面标题" />
                    </el-form-item>
                    <el-form-item label="Logo地址">
                        <el-input style="width: 500px;" v-model="$store.state.systemInfo.webLogo"
                            placeholder="可以引用外部链接" />
                    </el-form-item>
                    <el-form-item label="导航栏标题">
                        <el-input style="width: 500px;" v-model="$store.state.systemInfo.navTitle"
                            placeholder="首页顶部导航标题" />
                    </el-form-item>
                    <el-form-item label="关于博主">
                        <el-input style="width: 500px;" v-model="$store.state.systemInfo.aboutBlogger"
                            placeholder="跳转链接" />
                    </el-form-item>
                    <el-button type="primary" round style="margin-left: 50px;" @click="saveBasicInfo">保存</el-button>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="底部信息">
                <el-form label-width="120px">
                    <el-form-item label="底部引言">
                        <el-input style="width: 500px;" v-model="$store.state.systemInfo.underIntroduction"
                            placeholder="自定义" />
                    </el-form-item>
                    <el-form-item label="Powered by">
                        <el-input style="width: 500px;" v-model="$store.state.systemInfo.underPoweredBy"
                            placeholder="自定义" />
                    </el-form-item>
                    <el-form-item label="Copyright">
                        <el-input style="width: 500px;" v-model="$store.state.systemInfo.underCopyright"
                            placeholder="自定义" />
                    </el-form-item>
                    <el-form-item label="互联网ICP备案">
                        <el-input style="width: 500px;" v-model="$store.state.systemInfo.underIcp" placeholder="自定义" />
                    </el-form-item>
                    <el-button type="primary" round style="margin-left: 50px;" @click="saveBasicInfo">保存</el-button>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="Shields">
                <el-form label-width="120px">
                    <div v-for="item in shields" :key="item.id">
                        <el-form-item label="Shields Url">
                            <el-input style="width: 500px;" v-model="item.shieldsUrl" placeholder="自定义" />
                        </el-form-item>
                        <el-form-item label="跳转 Url">
                            <el-input style="width: 500px;" v-model="item.url" placeholder="自定义" />
                        </el-form-item>
                        <el-divider />
                    </div>
                    <el-button type="primary" round style="margin-left: 50px;" @click="saveShields">保存</el-button>
                </el-form>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script setup>
import { inject, onMounted, ref } from "@vue/runtime-core";
import store from '../../../../store'
import { ElMessage } from 'element-plus'

    const axios = inject('$axios')
    const qs = inject('$qs')
    let shields = ref('')

    function saveBasicInfo(){
        axios({
            url:'system/update/info',
            method:'POST',
            data:qs.stringify(store.state.systemInfo)
        }).then(res => {
            ElMessage({
                message: '保存成功.',
                type: 'success',
            })
        })
    }

    function getSystemInfo() {
        axios({
            url: '/system/get/info',
            method: 'GET'
        }).then(res => {
            if (res.data.code === '200') {
                store.commit('getSystemInfo', res.data.data)

                let $favicon = document.querySelector('link[rel="icon"]');
                if ($favicon !== null) {
                    $favicon.href = res.data.data.webLogo;
                } else {
                    $favicon = document.createElement("link");
                    $favicon.rel = "icon";
                    $favicon.href = res.data.data.webLogo;
                    document.head.appendChild($favicon);
                }
            }
        })
    }

    function listShields(){
        axios({
            url: '/system/list/shields',
            method: 'GET'
        }).then(res => {
            if (res.data.code === '200') {
                shields.value = res.data.data
            }
        })
    }

    function saveShields(){
        axios({
            url: '/system/update/shields',
            method: 'POST',
            data:shields.value
        }).then(res => {
            if (res.data.code === '200') {
                ElMessage({
                    message: '保存成功.',
                    type: 'success',
                })
            }
        })
    }

    onMounted:{
        getSystemInfo()
        listShields()
    }
</script>
