/*
* 调用后台批量删除的方法
* */
function deleteBatch(basePath) {
   $("#mainForm").attr("action",basePath + "DeleteBatch");
   $("#mainForm").submit();

}