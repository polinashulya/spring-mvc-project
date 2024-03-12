//package com.example.web.tag;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.web.util.TagUtils;
//
//
//import java.util.Map;
//import java.util.Objects;
//
//@Getter
//@Setter
//@NoArgsConstructor
//public class ConstantsTagHandler extends TagSupport {
//
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * The scope in which the exposed map will be stored.
//     */
//    private String scope = TagUtils.SCOPE_PAGE;
//
//    /**
//     * The name of the scoped attribute in which the constants will be stored.
//     */
//    private String var;
//
//
//    /**
//     * Expose the constants for a class as a scoped attribute.
//     *
//     * @return A constant that identifies what the container should do next.
//     * @throws JspException if a fatal error occurs.
//     */
//    @Override
//    public int doStartTag() throws JspException {
//        if (Objects.nonNull(var)) {
//            Map<String, Object> constants;
//            try {
//                constants = ClassReflectionUtils.getClassConstants();
//            } catch (final IllegalArgumentException e) {
//                throw new JspTagException("Illegal argument: " + e);
//            } catch (final IllegalAccessException e) {
//                throw new JspTagException("Illegal access: " + e);
//            }
//            if (!constants.isEmpty()) {
//                pageContext.setAttribute(var, constants, TagUtils.getScope(scope));
//            }
//        }
//
//        return SKIP_BODY;
//    }
//
//    /**
//     * Free up any resources being used by this tag handler.
//     */
//    @Override
//    public void release() {
//        super.release();
//        scope = null;
//        var = null;
//    }
//
//
//}