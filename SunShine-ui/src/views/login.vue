<template>
    <div class="login">
        <div class="loin_form">
            <el-card shadow="hover">
                <p class="title">登录</p>
                <el-form ref="form" :model="form" :rules="loginRules">
                    <el-form-item prop="userName">
                        <!-- <el-input v-model="form.name"></el-input> -->
                        <input class="input" placeholder="用户名" v-model="form.userName" />
                    </el-form-item>
                    <el-form-item prop="password">
                        <!-- <el-input v-model="form.password"></el-input> -->
                        <input class="input" placeholder="密码" v-model="form.password" @keyup.enter="handleLogin" />
                    </el-form-item>
                    <el-button round @click="handleLogin()">登录</el-button>
                </el-form>

                <a href="/">注册</a>
                <a href="/">忘记密码</a>
                <a href="/">联系我们</a>
            </el-card>
        </div>

        <div class="test">
            <p class="sysName1">昆明地质勘探研究院</p>
            <p class="sysName2">工程项目管理平台</p>
        </div>
        <div class="el-login-footer">
            <span>Copyright © 2023-2024 Aerlany All Rights Reserved.</span>
        </div>
    </div>


</template>

<script>
export default {
    data() {
        return {
            form: {
                userName: "admin",
                password: "000000"
            },
            loginRules: {
                userName: [
                    { required: true, trigger: "blur", message: "请输入您的账号" }
                ],
                password: [
                    { required: true, trigger: "blur", message: "请输入您的密码" }
                ],
                code: [{ required: true, trigger: "change", message: "请输入验证码" }]
            },
        }
    },
    methods: {
        handleLogin() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    this.$modal.loading("登陆中");
                    this.$store.dispatch("Login", this.form).then(() => {
                        this.$router.push({ path: "/" });
                        this.$modal.closeLoading();
                    }).catch(() => {
                        this.$modal.closeLoading();
                    });

                }
            })
        }
    }
}
</script>

<style lang="scss">
.login {
    background-color: #0093E9;
    background-image: linear-gradient(90deg, #0093E9 0%, #ffffff 100%);
    /* position: fixed; */
    width: 100vw;
    height: 100vh;

    .loin_form {
        position: fixed;
        top: 20%;
        right: 20%;
        width: 20%;
        height: 100%;
        text-align: center;
        border: none;
    }

    .title {
        font-family: Georgia, 'Times New Roman', Times, serif;
        text-align: center;
        font-size: 25px;
        font-weight: 600;
        margin: 30px auto;
        color: grey;
    }

    .el-card {
        border: 1px solid rgba(255, 255, 255, 0.1);
        height: 50%;
        transition: 0.3s;
        box-shadow: 0 25px 45px rgba(0, 0, 0, 0.1);
        background: rgba(255, 255, 255, 0.1);
    }

    .el-card:hover {
        border-radius: 35px;
        box-shadow: 0 25px 45px rgba(0, 0, 0, 0.1);
        transition: 0.3s;
    }

    p {
        font-size: 10px;
        position: relative;
    }

    .el-form-item {
        width: 100%;
        margin: 20px auto;
    }

    .el-button {
        font-family: Georgia, 'Times New Roman', Times, serif;
        margin: 10px auto;
        width: 150px;
    }

    a {
        margin-left: 5px;
        font-size: 10px
    }

    .sysName1 {
        position: fixed;
        font-family: Georgia, 'Times New Roman', Times, serif;
        font-size: 60px;
        top: 200px;
        // background-color: #fff;
        left: 100px;
        width: 50%;
    }

    .sysName2 {
        position: fixed;
        font-family: Georgia, 'Times New Roman', Times, serif;
        color: rgb(87, 87, 87);
        font-size: 60px;
        top: 300px;
        left: 100px;
        width: 50%;
    }
}

.el-login-footer {
    height: 40px;
    line-height: 40px;
    position: fixed;
    bottom: 0;
    width: 100%;
    text-align: center;
    color: #000000;
    font-family: Arial;
    font-size: 12px;
    letter-spacing: 1px;
}

.input {
    border: none;
    height: 40px;
    padding-left: 20px;
    margin: 0 auto;
    width: calc(100% / 1.2);
    background: rgba(255, 255, 255, 0.2);
    outline: none;
    border-radius: 35px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}
</style>