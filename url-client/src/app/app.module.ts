import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatTooltipModule } from '@angular/material/tooltip';
import {MatCardModule} from '@angular/material/card';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { urlService } from './service/url.service';
import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { shortLyComponent } from './component/short-ly.component';
import { routing } from './app.routing';
import { AuthService } from './service/auth.service';
import { HttpErrorHandler } from './utils/http-error-Handler';


@NgModule({
  declarations: [
    AppComponent,
    shortLyComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatSidenavModule,
    MatInputModule,
    MatFormFieldModule,
    MatTooltipModule,
    MatCardModule,
    FormsModule,
    ReactiveFormsModule,
    // import HttpClientModule after BrowserModule.
    HttpClientModule,
    routing
  ],
  providers: [
    urlService,
    AuthService,
    HttpErrorHandler
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
