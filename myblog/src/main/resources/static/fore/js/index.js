new Vue({
    el: '#app',
    data: {
        userInfo: { // 添加的用户信息
            name: '',
            gender: '',
            phoneNum: '',
            birthday: '',
        },
        tableData: [{
            name: '风花雪月',
            gender: '女生',
            phoneNum: '18909898767',
            birthday: '2016-05-02'
        }],
        dialogVisible: false,//是否显示修改用户的弹框
        editObj: {
            name: '',
            gender: '',
            phoneNum: '',
            birthday: '',
        },
        userIndex: 0,
        fileList: []
    },
    methods: {
        addUser: function () { //添加
            if (!this.userInfo.name){
                this.$message({
                    message: '请输入姓名！',
                    type: 'warning'
                });
                return;
            }
            if (!this.userInfo.gender){
                this.$message({
                    message: '请输入性别！',
                    type: 'warning'
                });
                return;
            }
            if (!this.userInfo.phoneNum){
                this.$message({
                    message: '请输入电话！',
                    type: 'warning'
                });
                return;
            }
            if (!/^1[3456789]\d{9}$/.test(this.userInfo.phoneNum)){//正则校验电话格式是否对
                this.$message({
                    message: '请输入正确的电话号！',
                    type: 'warning'
                });
                return;
            }
            if (!this.userInfo.birthday){
                this.$message({
                    message: '请选择生日！',
                    type: 'warning'
                });
                return;
            }
            this.tableData.push(this.userInfo);//添加输入的用户
            this.userInfo = {//清空输入框数据
                name: '',
                gender: '',
                phoneNum: '',
                birthday: '',
            }
        },
        delUser: function (index) {//删除用户
            // console.log(index)
            this.$confirm('确认删除？')
                .then(_ => {
                    this.tableData.splice(index, 1)//splice删除 vue改造过
                })
                .catch(_ => {});
        },
        editUser: function(item, index){//编辑数据
            //console.log(item)
            this.userIndex = index;
            this.editObj = {
                name: item.name,
                gender: item.gender,
                phoneNum: item.phoneNum,
                birthday: item.birthday,
            }
            this.dialogVisible = true;
        },
        handleClose: function () {
            this.dialogVisible = false;
        },
        confirm: function () {
            this.dialogVisible = false;
            Vue.set(this.tableData, this.userIndex, this.editObj);
            //this.tableData[this.userIndex] = this.editObj; 不会动态响应
        },


        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        handleExceed(files, fileList) {
            this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
        },
        beforeRemove(file, fileList) {
            return this.$confirm(`确定移除 ${ file.name }？`);
        }
    }
});