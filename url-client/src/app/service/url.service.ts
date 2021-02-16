import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from "src/environments/environment";
import { url } from "../dto/url-dto";
import { HandleError, HttpErrorHandler } from "../utils/http-error-Handler";
import { _errorHandler } from "../utils/response-utils";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    responseType: 'json',
    'access-control-allow-origin':"*"
  })
};;

@Injectable()
export class urlService {

  private URL: string = environment.apiUrl;
  private handleError: HandleError;


  constructor( private http: HttpClient,
    httpErrorHandler: HttpErrorHandler) {
    this.handleError = httpErrorHandler.createHandleError('URLService');
  }
    
  /** POST: add a new hero to the database */
  convertUrl(url: url): Observable<url> {
    return this.http.post<url>(this.URL+'/shortUrl/shorten', url, httpOptions)
      .pipe(
        catchError(this.handleError('convert', url))
      );
  }

}
