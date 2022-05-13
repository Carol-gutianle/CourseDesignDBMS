<template>
  <div >
    <div style="height: 40px">
    </div>
    <div style="text-align: center;width: 100%; padding-left: 400px;margin: auto">
      <el-card style="width: 40%; margin: 10px">

        <i class="el-icon-user-solid"></i>
        <p>游客登录</p>

        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="gocaozuo">游客登录</el-button>
        </div>

      </el-card>
    </div>
  </div>
</template>

<script>

export default {
  name: "tourist_denglu",
  methods: {
    gocaozuo(){
      this.$router.push({
        path:'../tourist_caozuo'
      })
    },
  }

}
</script>

<style scoped>

</style>
