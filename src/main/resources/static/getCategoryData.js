function getCategoryData() {
    var cate = $('#categoryInputer').map(function () { return $(this).val(); }).get();
    console.log(cate);
    return cate;
}
