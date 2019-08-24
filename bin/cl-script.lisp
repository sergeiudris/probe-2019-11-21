#!/usr/bin/sbcl --script

(write-line "hello world!")

(defun hello ()
 (print "hello there!")
)

(defun main ()
  (let ( (path (car *posix-argv*))
         (fnname (car (rest *posix-argv*)))
             )
             (print fnname)
             (funcall (symbol-function (find-symbol (string-upcase fnname) )))
              )
  
  ;; (print 3)
  ;; (print (rest ))
) 

;; (main)

;; (funcall (string-upcase "main"))

;; (symbol-function (find-symbol (string-upcase "main")))
;; (print (symbol-function (find-symbol "MAIN")))

;; (main)
(defun hi ()
 (print "hi there!")
)



(funcall (symbol-function (find-symbol "MAIN")))

