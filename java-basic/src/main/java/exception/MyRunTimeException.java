package exception;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Floweryu
 * @date 2023/3/26 20:56
 */
public class MyRunTimeException extends IllegalStateException {
    private final Set<Integer> exceptions = new LinkedHashSet<>();
    
    public void addExceptionDetail(Integer value) {
        this.exceptions.add(value);
    }
    
    
    @Override
    public String getMessage() {
        return "运行时异常抛出的值: " + getExceptions();
    }
    
    public Set<Integer> getExceptions() {
        return this.exceptions;
    }
    
}
