import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.util.config.AnalysisScopeReader;
import com.ibm.wala.util.io.FileProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 该类用来构建AnalysisScope并返回
 */
public class SomeClass {
  public AnalysisScope loadClass(ArrayList<String> file_paths)
      throws IOException, InvalidClassFileException {
    ClassLoader classloader = SomeClass.class.getClassLoader();
    File exclusionFile = new FileProvider().getFile("MyExclusion.txt");//包含排除JAVA原生类的文件

    //构建AnalysisScope
    AnalysisScope scope =
        AnalysisScopeReader.readJavaScope(
            "scope.txt", /*Path to scope file*/
            exclusionFile, /*Path to exclusion file*/
            classloader);

    //遍历把所有.class文件加进scope里
    for (int i = 0; i < file_paths.size(); i++) {
      File file = new FileProvider().getFile(file_paths.get(i));
      scope.addClassFileToScope(ClassLoaderReference.Application, file);
    }

    return scope;
  }
}
