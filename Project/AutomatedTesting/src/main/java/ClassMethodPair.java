/**
 * 该类记录一个class-method对，在本项目中用来记录所有测试用例的class-method对
 */
public class ClassMethodPair {
    String className;
    String methodName;
    public ClassMethodPair(String c,String m){
        className=c;
        methodName=m;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }
}
