# onSaveInstanceStateEasy

### 特性

```
简化  
onSaveInstanceState(Bundle outState)
```

### 使用

```
public class MainActivity extends AppCompatActivity {
    @SaveStatesAnno("key")  //要保存的数据添加SaveStatesAnno  key是保存数据到Bundle用的key
    String ss;
}

上面代码等价于

public class MainActivity extends AppCompatActivity {
    String dataToStore;
    public void onSaveInstanceState(Bundle var1) {
        var1.putSerializable("key",dataToStore);
        super.onSaveInstanceState(var1);
    }
}
```


```
Todo

1:目前只支持Serializable类型，支持更多
2:添加读取操作
```

