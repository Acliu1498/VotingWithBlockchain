package egr401.prototype.app.config

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class AccountServiceImpl(private val oAuthAccountDetailWriterFactory: OAuthAccountDetailWriterFactory,
                         private val accountRepository: AccountRepository) : AccountService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val account = accountRepository.findByUsername(username)
                ?: throw UsernameNotFoundException("Could not find account with username $username!")


        return with(account) {
            User.withUsername(username)
                    .password(password)
                    .authorities("USER")
                    .build()
        }
    }

    override fun saveOAuth2Account(oAuth2Authentication: OAuth2Authentication): Account {
        val userAuthentication = oAuth2Authentication.userAuthentication
        val details = userAuthentication.details as Map<*, *>
        val accountWriter = oAuthAccountDetailWriterFactory
                .getAccountDetailWriter(oAuth2Authentication.oAuth2Request.clientId)
        val username = userAuthentication.principal as String

        return accountRepository.findByUsername(username)
                ?: accountRepository.save(accountWriter.createAccount(username, details))
    }
}