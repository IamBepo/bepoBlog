<template>
    <div style="background-color: #e7e7e7;height: 55px;width: 100%;display: flex;align-items: center;">
        <div style="padding: 0 10px;">
            <el-button type="primary" @click="changeNav">
                <el-icon><Fold /></el-icon>
            </el-button>
        </div>
    </div>
</template>

<script setup>
import { useStore } from 'vuex';
const store = useStore()

function changeNav(){
    store.commit('adminNav',!store.state.isCollapse)
}

</script>

<style>

</style>