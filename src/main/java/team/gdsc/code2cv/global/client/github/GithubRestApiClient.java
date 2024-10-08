package team.gdsc.code2cv.global.client.github;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import team.gdsc.code2cv.global.client.github.response.GithubCommitDetailInfoModel;
import team.gdsc.code2cv.global.client.github.response.GithubCommitInfoModel;
import team.gdsc.code2cv.global.client.github.response.GithubRepositoryInfoModel;
import team.gdsc.code2cv.global.client.github.response.GithubUserInfoModel;

@HttpExchange
public interface GithubRestApiClient {

	@GetExchange("/user/repos")
	ResponseEntity<List<GithubRepositoryInfoModel>> getRepositoryList(
		@RequestHeader("Authorization") String token, // Bearer token
		@RequestParam(value = "page", defaultValue = "1") int page, // 1, 2, 3, ...
		@RequestParam(value = "per_page", defaultValue = "30") int perPage,
		@RequestParam(value = "sort", defaultValue = "updated") String sort, // created, updated, pushed, full_name
		@RequestParam(value = "since", required = false) String since,
		@RequestParam(value = "before", required = false) String before,
		@RequestParam(value = "visibility", defaultValue = "public") String visibility,
		@RequestParam(value = "affiliation", defaultValue = "owner,collaborator") String affiliation
	);

	@GetExchange("/user")
	ResponseEntity<GithubUserInfoModel> getUserInfo(
		@RequestHeader("Authorization") String token
	);

	@GetExchange("/repos/{owner}/{repo}/commits")
	ResponseEntity<List<GithubCommitInfoModel>> getCommits(
		@RequestHeader("Authorization") String token,
		@PathVariable("owner") String owner,
		@PathVariable("repo") String repo,
		@RequestParam(value = "per_page", defaultValue = "30") int perPage,
		@RequestParam(value = "page", defaultValue = "1") int page
	);

	@GetExchange("/repos/{owner}/{repo}/commits/{sha}")
	ResponseEntity<GithubCommitDetailInfoModel> getCommitDetails(
		@RequestHeader("Authorization") String token,
		@PathVariable("owner") String owner,
		@PathVariable("repo") String repo,
		@PathVariable("sha") String sha
	);
}
