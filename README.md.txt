# AutomatedTesting2020

### 181250175 杨雨佳

- 选题方向：经典自动化测试

- 文件夹介绍：
  - Demo：存放testSelection.jar
  - Report：存放五张类依赖图和方法依赖图，1-ALU、2-DataLog、3-BinaryHeap、4-NextDay、5-MoreTriangle各一张类依赖图和一张方法依赖图
  - Project：存放项目代码， 项目根目录为AutomatedTesting，程序入口为src/main/java下Start类的main函数

- 内容简介：使用WALA，新建以下几个类，实现类级/方法级测试用例选择：
  - Start：程序入口、分析输入参数、初始化
  - SomeClass：构建分析域
  - MakeGraph：使用WALA带的方法来遍历项目、生成依赖关系图
  - Selector：根据变更文件和依赖关系图进行测试用例选择
  - Graph：依赖图类
  - ClassMethodPair：class-method对类，在该项目中用于存放所有测试用例的class-method对

- 大体思路：
  - 1.运行jar包时，参数从Start的main函数里传进来，在main中进行参数的分析，得到选择类型（类级或方法级）、项目路径和变更文件路径，之后调用Start的initialize方法遍历项目路径得到所有.class文件的路径，用于下一步构建分析域
  - 2.调用SomeClass的loadClass方法构建分析域
  - 3.调用MakeGraph的analysis方法遍历所有类和所有方法构建依赖图
      •用CHA算法来构建调用图
      •用getPreNodes方法获得某节点的所有前驱节点，从而找到依赖关系
      •MakeGraph的draw方法调用Graph的makeDotFile方法生成相应的dot文件
      •返回类/方法依赖图和所有测试用例的class-method对，进入下一步测试选择过程
  - 4.调用Selector的decodeChange方法解析变更文件，最后根据选择类型调用exec_class_select或exec_method_select来进行类级/方法级测试用例选择，并输出最后的选择结果