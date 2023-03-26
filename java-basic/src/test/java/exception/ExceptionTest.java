package exception;

import org.junit.Test;

/**
 * @author zhangjunfeng
 * @date 2022/2/28 20:05
 */
public class ExceptionTest {
    
    private ExceptionMain exceptionMain;
    
    @Test
    public void exceptionTest() {
        ExceptionMain.exceptionTest();
    }
    
    
    @Test
    public void myExceptionTest() {
        MyRunTimeException myRunTimeException = new MyRunTimeException();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                myRunTimeException.addExceptionDetail(i);
            }
        }
        
        if (! myRunTimeException.getExceptions().isEmpty()) {
            throw myRunTimeException;
        }
    }
}
