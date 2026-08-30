import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptors: HttpInterceptorFn = (req, next) => {
  let token = localStorage.getItem('token');
  if (token) {
    const cloneReq = req.clone({ headers: req.headers.set('Authorization', `Bearer ${token}`) });
    return next(cloneReq);   
      }
  return next(req);
};

export const AuthInterceptorsProvider = [
  {
    provide: 'HTTP_INTERCEPTORS',
    useClass: authInterceptors,    
    multi: true
  } 
]