<%@page contentType="text/html" pageEncoding="UTF-8" %>

<img id="output"
     width="230px"
     height="230px"
     alt="picture"
     src="${userForm.image.imageUrl ne '' && userForm.image.imageUrl ne 'data:image/jpg;base64,' ?
     userForm.image.imageUrl :
     'https://kolesogizni.com/images/author_img/author-without-photo.jpg'}"/>

