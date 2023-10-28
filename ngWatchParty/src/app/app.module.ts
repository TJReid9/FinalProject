import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavigationComponent } from './components/navigation/navigation.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { UsersComponent } from './components/users/users.component';
import { HomeComponent } from './components/home/home.component';
import { VenueComponent } from './components/venue/venue.component';
import { PartiesComponent } from './components/parties/parties.component';
import { RegisterComponent } from './components/register/register.component';
import { TeamsComponent } from './components/teams/teams.component';
import { IncompletePipe } from './pipes/incomplete.pipe';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    LoginComponent,
    LogoutComponent,
    NotFoundComponent,
    UsersComponent,
    HomeComponent,
    VenueComponent,
    PartiesComponent,
    RegisterComponent,
    TeamsComponent,
    IncompletePipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
  ],
  providers: [IncompletePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
