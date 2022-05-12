<template>
  <div>
    <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
      <el-radio-button :label="false">展开</el-radio-button>
      <el-radio-button :label="true">收起</el-radio-button>
    </el-radio-group>
    <el-menu   router default-active="path" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse">

      <el-submenu index="1">

        <template slot="title">
          <i class="el-icon-edit"></i>
          <span slot="title">管理</span>
        </template>

<!--        <el-menu-item-group>-->
<!--          <el-menu-item index="add_deletelike_person">person数据管理(之后删</el-menu-item>-->
<!--        </el-menu-item-group>-->

        <el-menu-item-group>
          <el-menu-item index="student_denglu">学生登录</el-menu-item>
        </el-menu-item-group>

        <el-menu-item-group>
          <el-menu-item index="teacher_denglu">老师登录</el-menu-item>
        </el-menu-item-group>

        <el-menu-item-group>
          <el-menu-item index="tourist_denglu">游客访问</el-menu-item>
        </el-menu-item-group>

<!--        <el-menu-item-group>-->
<!--          <el-menu-item index="student_caozuo">学生操作(之后改</el-menu-item>-->
<!--        </el-menu-item-group>-->

<!--        <el-menu-item-group>-->
<!--          <el-menu-item index="teacher_caozuo">老师操作(之后改</el-menu-item>-->
<!--        </el-menu-item-group>-->

<!--        <el-menu-item-group>-->
<!--          <el-menu-item index="teacher_caozuo2">老师进阶操作(之后改</el-menu-item>-->
<!--        </el-menu-item-group>-->

<!--        <el-menu-item-group>-->
<!--          <el-menu-item index="tiku">题库内容(之后改</el-menu-item>-->
<!--        </el-menu-item-group>-->


<!--          <el-menu-item index="add_deletelike_user" >user数据管理(之后删</el-menu-item>-->


      </el-submenu>

<!--      <el-menu-item index="home">-->
<!--        <i class="el-icon-location"></i>-->
<!--        <span slot="title">person数据库</span>-->
<!--      </el-menu-item>-->
<!--      <el-menu-item index="userhome">-->
<!--        <i class="el-icon-location"></i>-->
<!--        <span slot="title">users数据库</span>-->
<!--      </el-menu-item>-->

    </el-menu>
  </div>
</template>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 100vh;
}
</style>

<script>
export default {
  name: "mymenu",
  data() {
    return {
      isCollapse: true,
      path:this.$route.path
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style scoped>

</style>
