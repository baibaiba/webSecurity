(function () {
    // 监听
    $("#test").on('click', function () {
        arr();
    });

    // 自定义函数
    function arr() {
        var arr1 = [];
        var arr2 = [];
        var obj = {
            name: '白阁',
            type: 1
        };
        var obj1 = {
            name: '武松',
            type: 2
        };

        var obj2 = {
            name: '鲁智深',
            type: 1
        };

        arr1.push(obj);
        arr1.push(obj1);
        arr1.push(obj2);
        arr2.push(obj);
        arr2.push(obj2);
        // 并集
        var res = arr1.concat(arr2);
        for (var i = 0; i < arr1.length; i++) {
            for (var j = 0; j < arr2.length; j++) {
                if (arr2[j].name === res[i].name) {
                    res[i].type = 3;
                }
            }
        }
        console.log(res);
    }
});