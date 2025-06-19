package com.ms.user.producers;

import com.ms.user.dtos.EmailDto;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // para recuperar o nome da fila
    // routingkey -> a que tem que passar para enviar as mensagens quando se usa um exchange do tipo default, ela tem que ser o mesmo nome da fila criada
    @Value( value = "${broker.queue.email.name}")
    private String routingKey;

    // método para enviar a mensagem
    public void publishMessageEmail( UserModel userModel ) {
        var emailDto = new EmailDto();

        emailDto.setUserId( userModel.getUserId() );
        emailDto.setEmailTo( userModel.getEmail() );
        emailDto.setSubject( "Cadastro Realizado com Sucesso" );
        emailDto.setText( userModel.getName() + ", seja bem vindo(a) \nAgradecemos seu cadastro, aproveite agora todos os recursos da nossa plataforma!" );

        rabbitTemplate.convertAndSend("", routingKey, emailDto);

    }

}
