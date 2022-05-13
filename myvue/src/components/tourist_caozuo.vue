<template>
  <div >
    <div style="height: 40px">
    </div>
    <div style="text-align: center;width: 100%; padding-left: 400px;margin: auto">
      <el-card style="width: 40%; margin: 10px">

        <i class="el-icon-user-solid"></i>
        <p>游客操作</p>

        <el-divider></el-divider>

        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="gotiku">查看题库</el-button>
        </div>

        <el-divider></el-divider>

        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="goziliao">查看资料</el-button>
        </div>

      </el-card>
    </div>
  </div>
</template>

<script>

export default {
  name: "tourist_caozuo",
  methods: {
    gotiku(){
      this.$router.push({
        path:'../tiku'
      })
    },

    goziliao(){
      this.$router.push({
        path:'../ziliao'
      })
    },

  }

}
</script>

<style scoped>

</style>
