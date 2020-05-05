# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# 设置include文件夹的地址
include_directories(${CMAKE_SOURCE_DIR}/include)

#编译头文件
#定义全局 my_source_path 变量
file(GLOB my_source_path ${CMAKE_SOURCE_DIR}/*.cpp ${CMAKE_SOURCE_DIR}/*.c)
add_library(lynxzCV SHARED ${my_source_path})

#添加动态链接库
add_library(libopencv_java4 SHARED IMPORTED)
#set_target_properties(libopencv_java4 PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_java4.so)
set_target_properties(libopencv_java4 PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/../jniLibs/armeabi-v7a/libopencv_java4.so)

# 在ndk中查找log库 取别名log-lib
find_library(log-lib log)

# 在ndk中查找jnigraphics库 取别名jnigraphics-lib
# jnigraphics包含图形操作的库
find_library(jnigraphics-lib jnigraphics)

#设置 target 需要链接的库
target_link_libraries(
        lynxzCV
        libopencv_java4
        ${jnigraphics-lib}
        ${log-lib})