package cat.tecnocampus.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAdvice.class);


    @Pointcut("execution(* cat.tecnocampus.controllers.ControllerDAO.*(..))")
    public void pointcutClassroom() {
        logger.info("Going to get a classroom method");
    }

    @Before("pointcutClassroom()")
    public void beforeListClassrooms() {
        logger.info("Working with a classroom");
    }

    @Pointcut("execution(* cat.tecnocampus.controllers.ControllerDAO.*find*(..))")
    public void pointcutFind() {
        logger.info("Going to show a method with find word");
    }

    @After("pointcutFind()")
    public void afterFind() {
        logger.info("Finding Classrooms...");
    }

    @Pointcut("execution(* cat.tecnocampus.controllers.ControllerDAO.insertBatch(..))")
    public void pointcutBach() {
        logger.info("Insterting batch of Classrooms");
    }

    @Around("pointcutBach()")
    public int[] dealRequestParam(ProceedingJoinPoint jp) {
        try {
            logger.info("before multiple insert");
            int[] res = (int[]) jp.proceed();
            logger.info("after multiple insert");
            return res;
        } catch (Throwable throwable) {
            logger.info("Showing classrooms: Something went wrong");
            throwable.printStackTrace();
            return new int[]{};
        }
    }
}
