# 封装opencv lib,方便其他项目使用
本项目使用 [opencv4.2](https://opencv.org/releases/), 下载解压后,假设解压目录为 `opencv4.2`,方便后续指代

## 导入opencv4.2/sdk/java, 命名为 opencv_lib

##  opencv_lib 说明
`src/main/cpp/` 是为了自定义功能
    其中 `include/opencv2/` 是导入自 `opencv4.2/sdk/native/jni/include/opencv2/`

## 踩坑记录
### 报错: library "libc++_shared.so" not found
[解决方案](https://blog.csdn.net/Fozei/article/details/103676970)
本项目通过添加cmake编译脚本,导入 libc++_shared.so

```gradle
// --- opencv_lib/build.gradle ---
apply plugin: 'com.android.library'
android {
    defaultConfig {
        externalNativeBuild {
            cmake {
                cppFlags "-std=c++14"
                arguments "-DANDROID_STL=c++_shared" // 使用c++_shared.so
                abiFilters 'armeabi-v7a'
            }
        }
    }

    externalNativeBuild {
        cmake {
            path "src/main/cpp/CMakeLists.txt"
            version "3.10.2"
        }
    }

    sourceSets {
        main {
            jniLibs.srcDirs += ['src/main/jniLibs/']
        }
    }
}
```

### 报错: error adding symbols: File in wrong format clang++: error: linker command failed with exit code 1
原因: 我在 `src/main/cpp/CMakeLists.txt` 中 set_target_properties 直接指定了架构 armeabi-v7a, 但 build.gradle 中却没有做对应过滤
处理: 添加 abiFilter

```gradle
// --- opencv_lib/build.gradle ---
apply plugin: 'com.android.library'
android {
    defaultConfig {
        externalNativeBuild {
            cmake {
                abiFilters 'armeabi-v7a'
            }
        }
    }
}
```