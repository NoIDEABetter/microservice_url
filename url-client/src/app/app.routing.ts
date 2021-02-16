import { RouterModule, Routes } from "@angular/router";
import { shortLyComponent } from "./component/short-ly.component";

const appRoutes: Routes = [

    { path: '', component: shortLyComponent }
];

export const routing = RouterModule.forRoot(appRoutes);