package com.sbrf.reboot.bankapplication.service;

import com.sbrf.reboot.bankapplication.entities.Balance;
import com.sbrf.reboot.bankapplication.entities.User;
import com.sbrf.reboot.bankapplication.repositories.BalanceRepository;
import com.sbrf.reboot.bankapplication.service.security.WhoAmIService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
public class BalanceServiceJpaTest {
    @TestConfiguration
    static class DataServiceImplTestContextConfiguration {

        @Bean
        public WhoAmIService whoAmIService() {
            return Mockito.mock(WhoAmIService.class);
        }

        @Bean
        public BalanceRepository balanceRepository() {
            return Mockito.mock(BalanceRepository.class);
        }
    }

    @Autowired
    private WhoAmIService whoAmIService;

    @Autowired
    private BalanceRepository balanceRepository;

    @MockBean
    private BalanceServiceJpa balanceServiceJpa;

    @Before
    public void init() {
    }

    @Test
    public void shouldGetBalanceCorrectly() {
        final User user = new User();
        final Balance balance = new Balance();
        Long valueExpected = 10L;
        balance.setAmount(valueExpected);

        Mockito.when(whoAmIService.whoami()).thenReturn(user);
        Mockito.when(balanceRepository.findByUserId(Mockito.anyInt())).thenReturn(balance);

        Assert.assertEquals(
                valueExpected,
                balanceServiceJpa.getBalance()
        );
    }
}
