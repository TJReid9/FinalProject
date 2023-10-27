import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { UsersComponent } from './components/users/users.component';
import { HomeComponent } from './components/home/home.component';
import { VenueComponent } from './components/venue/venue.component';
import { PartiesComponent } from './components/parties/parties.component';
import { RegisterComponent } from './components/register/register.component';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'users', component: UsersComponent },
  { path: 'venues', component: VenueComponent },
  // { path: 'register', component: RegisterComponent },
   { path: 'parties', component: PartiesComponent },
   { path: 'parties/:partyId', component: PartiesComponent },
   { path: 'venues/:venueId', component: VenueComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
