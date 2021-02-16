import { HttpHeaders } from "@angular/common/http";

export function 
getHeaders() : HttpHeaders{
    return  new HttpHeaders()
            .set('Content-Type', 'application/json; charset=utf-8')
            .set('Authorization', sessionStorage.getItem('currentUser'));
}