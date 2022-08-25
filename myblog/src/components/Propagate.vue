<template>
    <div style="height: 35px;box-shadow: 0 4px 8px 6px rgb(113 118 122 / 6%);margin-bottom: 12px;border-radius: 13px;background-color: white;">
        <div style="display: flex;align-items: center;height: 100%;padding-left: 10px;color: #918282;font-size: 17px;">
            <el-icon><Coordinate /></el-icon>
            <div style="padding-left: 8px;">
                {{content}}
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";

    let content = ref('小导航')
</script>