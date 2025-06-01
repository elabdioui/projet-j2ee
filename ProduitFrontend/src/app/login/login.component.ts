import { Component, OnInit } from '@angular/core';
import { User } from '../model/user.model';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user = new User();
  err: number = 0;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  onLoggedin() {
    this.authService.login(this.user).subscribe((data) => {
      let jwToken = data.headers.get('Authorization')!;
      this.authService.saveToken(jwToken);
      this.router.navigate(['/']);
    }, (err) => {
      this.err = 1;
    });
  }
}
