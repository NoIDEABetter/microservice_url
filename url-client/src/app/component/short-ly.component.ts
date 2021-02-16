import { Component } from "@angular/core";
import { FormControl, Validators } from "@angular/forms";
import { url } from "../dto/url-dto";
import { urlService } from '../service/url.service';


@Component({
    templateUrl: './short-ly.component.html',
    styleUrls: ['./short-ly.css']
  })
  
export class shortLyComponent {

  newUrl: url;

    urlFormControl = new FormControl('', [
        Validators.required
      ]);
    
      constructor(
        private urlService: urlService
        ){
      }
    
    
      convertUrl(){
        var jsonData = new url;
        jsonData.longUrl = this.urlFormControl.value;
        this.urlService.convertUrl(jsonData).subscribe(data =>
        {
          console.log(data);
          this.newUrl=data;
          console.log(this.newUrl);
        });
      }
}