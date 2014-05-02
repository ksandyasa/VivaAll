package id.co.viva.pialadunia.object;

public class FrontLiveScore {
	private String match_id, match_date, match_time, home_score, away_score, team_a, team_b, nickname_a, nickname_b, team_logo_1, team_logo_2;
	
	public FrontLiveScore(String match_id, String match_date, String match_time, String home_score, String away_score, String team_a, String team_b, String nickname_a, String nickname_b, String team_logo_1, String team_logo_2) {
		this.setMatchId(match_id);
		this.setMatchDate(match_date);
		this.setMatchTime(match_time);
		this.setHomeScore(home_score);
		this.setAwayScore(away_score);
		this.setTeamA(team_a);
		this.setTeamB(team_b);
		this.setNicknameA(nickname_a);
		this.setNicknameB(nickname_b);
		this.setTeamLogo1(team_logo_1);
		this.setTeamLogo2(team_logo_2);
	}

	public String getMatchId() {
		return match_id;
	}

	public void setMatchId(String match_id) {
		this.match_id = match_id;
	}

	public String getMatchDate() {
		return match_date;
	}

	public void setMatchDate(String match_date) {
		this.match_date = match_date;
	}

	public String getMatchTime() {
		return match_time;
	}

	public void setMatchTime(String match_time) {
		this.match_time = match_time;
	}

	public String getHomeScore() {
		return home_score;
	}

	public void setHomeScore(String home_score) {
		this.home_score = home_score;
	}

	public String getAwayScore() {
		return away_score;
	}

	public void setAwayScore(String away_score) {
		this.away_score = away_score;
	}

	public String getTeamA() {
		return team_a;
	}

	public void setTeamA(String team_a) {
		this.team_a = team_a;
	}

	public String getTeamB() {
		return team_b;
	}

	public void setTeamB(String team_b) {
		this.team_b = team_b;
	}

	public String getNicknameA() {
		return nickname_a;
	}

	public void setNicknameA(String nickname_a) {
		this.nickname_a = nickname_a;
	}

	public String getNicknameB() {
		return nickname_b;
	}

	public void setNicknameB(String nickname_b) {
		this.nickname_b = nickname_b;
	}

	public String getTeamLogo1() {
		return team_logo_1;
	}

	public void setTeamLogo1(String team_logo_1) {
		this.team_logo_1 = team_logo_1;
	}

	public String getTeamLogo2() {
		return team_logo_2;
	}

	public void setTeamLogo2(String team_logo_2) {
		this.team_logo_2 = team_logo_2;
	}
}