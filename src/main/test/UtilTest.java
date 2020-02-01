
import com.teacher.study.util.CodeUtil;
import com.teacher.study.util.FtpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;


public class UtilTest {
    @Test
    public void a(){
        String uri="http://123.56.42.31/vido/bfd834a2-5e0a-421c-91c8-9e2772105c6f.mp4";
        String url=uri.substring(20);
        String substring = uri.substring(uri.lastIndexOf(".") + 1);
        System.out.println(substring);
       /*// HttpServletRequest request=new ContextExposingHttpServletRequest();
        String s=Index.class.getResource("/").toString();
        String path="jar:file:/demo/study-0.0.1-SNAPSHOT.jar!/BOOT-INF/classes!/";
        System.out.println(s);
        System.out.println(s.substring(0, s.indexOf("/",s.indexOf("/")+1 )));
       // System.out.println( request.getSession().getServletContext().getRealPath("/")));
        String a=path.substring(0, path.indexOf("/",path.indexOf("/")+1 ));
        System.out.println(a.substring(9));*/

        /*Double aDouble = Double.valueOf((3 / 10));
        /*System.out.println();
        System.out.println(Math.ceil(aDouble)==0);
        /*HttpServletRequest httpServletRequest=new DefaultMultipartHttpServletRequest();
        request.getRemoteAddr()*/
        /*FtpUtil.delFile("http://123.56.42.31/img/05195ed7-69d4-4b64-b437-460274293884.jpeg");*/
        //System.out.println(CodeUtil.createCode());
    }

}
