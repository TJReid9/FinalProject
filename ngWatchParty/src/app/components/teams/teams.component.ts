import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { config } from 'rxjs';
import { Sport } from 'src/app/models/sport';
import { Team } from 'src/app/models/team';
import { AuthService } from 'src/app/services/auth.service';
import { SportService } from 'src/app/services/sport.service';
import { TeamService } from 'src/app/services/team.service';
import { VenueService } from 'src/app/services/venue.service';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.css']
})
export class TeamsComponent {

  team: Team = new Team;
  teams: Team[] = [];
  selectedTeam: Team | null = null;
  editTeam: Team | null = null;
  addNewTeam: Team | null = null;
  newTeam: Team = new Team();
  sports: Sport[] = [];

  constructor(private teamService: TeamService,
    private sportService: SportService,
    private activatedRoute: ActivatedRoute,
    private router: Router, private auth: AuthService, private venueService: VenueService,
    private carouselconfig: NgbCarouselConfig){
      carouselconfig.interval = 2500;
    }

    loggedIn(): boolean {
      return this.auth.checkLogin();
     }

     loadTeams() {
      this.teamService.index().subscribe({
        next: (teams) => {
          this.teams = teams;
        },
        error: (problem) => {
          console.error('TeamsComponent.loadTeams(): error loading Teams:');
          console.error(problem);
        },
      });
    }
     loadSports() {
      this.sportService.index().subscribe({
        next: (sports) => {
          this.sports = sports;
        },
        error: (problem) => {
          console.error('SportsComponent.loadTeams(): error loading Sports:');
          console.error(problem);
        },
      });
    }

     ngOnInit(): void {
      this.loadTeams();
      this.activatedRoute.paramMap.subscribe({
        next: (params) => {
          let teamIdStr = params.get('teamId');
          if (teamIdStr) {
            let teamId = parseInt(teamIdStr);
            if (isNaN(teamId)) {
              this.router.navigateByUrl('/invalidTeamId');
            } else {
              this.teamService.show(teamId).subscribe({
                next: (team) => {
                  this.selectedTeam = null;
                },
                error: (nojoy) => {
                  console.error(
                    'TeamsComponent.show(): error getting Team:'
                  );
                  this.router.navigateByUrl('/invalidTeamId');
                },
              });
            }
          }
        },
      });
  }


  reload(): void{
   this.teamService.index().subscribe({
    next: (teams) => {
    this.teams = teams;
  },
  error: (problem) => {
    console.error('TeamsComponent.reload(): error loading Team: ');
    console.error(problem);
  },
  });
  }


  displayTeam(team: Team): void {
    this.selectedTeam = team;
  }

  displayAllTeams(): void{
    this.selectedTeam = null;
  }

  setEditTeam() {
    this.editTeam = Object.assign({}, this.selectedTeam);
  }

  displayAddNewWorkout(team: Team){
    this.addNewTeam = team;
  }


  updateTeam(team: Team, id: number) {
    console.log(team);
    this.teamService.update(id, team).subscribe({
      next: (teams) => {
        this.selectedTeam = this.editTeam;
        this.reload();
        this.editTeam = null;
      },
      error: (problem) => {
        console.error('TeamsComponent.reload(): error loading Team: ');
        console.error(problem);
      },
    });
  }

  addTeam(team: Team): void {
    this.teamService.create(team).subscribe({
      next: (result) => {
        this.reload();
        this.newTeam = new Team();
      },
      error: (nojoy) => {
        console.error('TeamsComponent.reload(): error loading Team: ');
        console.error(nojoy);
      },
    });
  }

  deleteTeam(id: number) {
    this.teamService.destroy(id).subscribe({
      next: (result) => {
        this.reload();

      },
      error: (nojoy) => {
        console.error('TeamsComponent.reload(): error loading Team:');
        console.error(nojoy);
      },
    });
  }


}



