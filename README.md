# 介绍
布局状态库

# 如何使用

## 引用依赖

## 简单使用

### 低入侵用法
注意如果要注入的子布局或 View 的父布局是 ConstraintLayout 等需要根据 ID 约束或依赖的局部，不推荐使用低入侵方式。

#### 在 View 中低入侵的用法，
```
val smallStatus = SmallStatus.bindTarget(view)
smallStatus.show<StateSuccess>()
smallStatus.show<StateError>()

// or

val smallStatus = SmallStatus.bindTarget(view)
smallStatus.show(StateSuccess::class.java)
smallStatus.show(StateError::class.java)
```

#### 在 Activity 中低入侵的用法，
```
val smallStatus = SmallStatus.bindTarget(this)
smallStatus.show<StateSuccess>()
smallStatus.show<StateError>()
```

#### 在 Fragment 中低入侵的用法，
```
private lateinit var mRootView: SmallStatusContainer

override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View {
    val inflate = inflater.inflate(R.layout.fragment_inject_fragment_status, container, false)
    mRootView = SmallStatus.bindTarget(inflate) {
        // 重试事件
        it.show<StateSuccess>()
    }
    return mRootView
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    mRootView.show<StateSuccess>()
    mRootView.show<StateError>()
}
```


### 布局用法
注意如果要注入的子布局或 View 的父布局是 ConstraintLayout 等需要根据 ID 约束或依赖的局部，推荐使用该方法。

```
<com.smallraw.widget.SmallStatusLayout
    android:id="@+id/layoutStatus"
    android:layout_width="match_parent"
    android:layout_height="350dp">

    <TextView
        android:id="@+id/tvTargetView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        android:text="Success"
        android:textSize="24sp"
        android:textStyle="bold" />
</com.smallraw.widget.SmallStatusLayout>
```

## 高级使用

### 监听点击事件
```
SmallStatus.bindTarget(view,{
    // 点击重试
},{
    // 点击关闭按钮（默认布局为提供此按钮）
})
```

```
val layoutStatus = findById<>(xxxxx);
//or
val layoutStatus = SmallStatus.bindTarget(view);

layoutStatus.setOnRetryEventListener {
    it.show<StateSuccess>()
}

layoutStatus.setOnCloseEventListener {
    it.show<StateSuccess>()
}
```

### 修改展示的状态布局的内容

```
val layoutStatus = findById<>(xxxxx);
//or
val layoutStatus = SmallStatus.bindTarget(view);

layoutStatus.show<StateError> {
    it.tvErrorMsg.text = "error"
}
```

### 自带默认布局的全局配置

可以在 Application 里配置
```
val config = SmallStateConfig.Builder()
    .alphaDuration(300)
    .errorIcon(R.mipmap.ic_state_error)
    .emptyIcon(R.mipmap.ic_state_empty)
    .emptyMsg(R.string.empty_msg)
    .loadingMsg(R.string.loading_msg)
    .errorMsg(R.string.error_msg)
    .build()

SmallStatus.globalConfig(config)
```

### 自带默认布局的局部配置

```
val config = SmallStateConfig.Builder()
    .alphaDuration(300)
    .errorIcon(R.mipmap.ic_state_error)
    .emptyIcon(R.mipmap.ic_state_empty)
    .emptyMsg(R.string.empty_msg)
    .loadingMsg(R.string.loading_msg)
    .errorMsg(R.string.error_msg)
    .build()

val layoutStatus = SmallStatus.bindTarget(view,config = config)

//or

val layoutStatus = findById<>(xxxxx)
layoutStatus.setConfig(config)
```

### 自定义状态局部

继承 StatePage 即可

```
class StateCustom : StatePage() {
    lateinit var customText: TextView
    lateinit var customImg: ImageView

    // 创建布局
    override fun onCreateStatePageView(
        context: Context,
        inflater: LayoutInflater,
        container: SmallStatusContainer
    ): View {
        return inflater.inflate(R.layout.small_state_custom, container, false)
    }

    // 创建布局
    override fun onConfigStateView(view: View, config: SmallStateConfig?) {
        customText = view.findById(R.id.xxx)
        customImg = view.findById(R.id.xxx)
    }

    // 是否开始点击重试功能
    override fun enableReload(): Boolean = false

    // 非强制重写，如果 enableReload() 开启重试功能，不重写或返回 null，则点击页面就会出发重试事件。
    open fun bindRetryView(): View? = customText

    // 非强制重写，如果不返回 null 则会注册点击关闭的事件。
    open fun bindCloseView(): View? = customImg
}

val layoutStatus = SmallStatus.bindTarget(view)
// 直接即可使用自定义的状态布局
layoutStatus.show<StateCustom>()
```

## 例子参考
[使用布局方式](https://github.com/SmallRaw/SmallStatusLayout/blob/master/simple/src/main/java/com/smallraw/smallrawstatelayout/simple/ViewStatusActivity.kt)

[使用低入侵方式](https://github.com/SmallRaw/SmallStatusLayout/blob/master/simple/src/main/java/com/smallraw/smallrawstatelayout/simple/ViewInjectStatusActivity.kt)

[Activity 使用方式](https://github.com/SmallRaw/SmallStatusLayout/blob/master/simple/src/main/java/com/smallraw/smallrawstatelayout/simple/ActivityInjectStatusActivity.kt)

[Fragment 使用方式](https://github.com/SmallRaw/SmallStatusLayout/blob/master/simple/src/main/java/com/smallraw/smallrawstatelayout/simple/InjectStatusFragment.kt)

[ViewBinding Fragment 使用方式](https://github.com/SmallRaw/SmallStatusLayout/blob/master/simple/src/main/java/com/smallraw/smallrawstatelayout/simple/ViewBindingInjectStatusFragment.kt)

[自定义 StatePage 使用方式](https://github.com/SmallRaw/SmallStatusLayout/blob/master/simple/src/main/java/com/smallraw/smallrawstatelayout/simple/CustomStatusActivity.kt)
