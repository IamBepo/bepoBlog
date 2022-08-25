<template>
    <div style="display: flex;width: 100%;">
        <div>
            <AdminNav></AdminNav>
        </div>
        <div style="width: 100%;min-height: 100%;display: flex;flex-direction: column">
            <div style="flex: 0;">
                <AdminTopNav></AdminTopNav>
            </div>
            <div style="background-color: #f9f7f7;flex: 1;padding: 20px 40px;">
                <router-view></router-view>
            </div>
        </div>
    </div>
</template>

<script setup>
import AdminNav from "./admin/AdminNav.vue";
import AdminTopNav from "./admin/AdminTopNav.vue";


</script>

<style>
ul.el-menu.el-menu--popup.el-menu--popup-right-start {
    padding: 0 !important;
}
</style>